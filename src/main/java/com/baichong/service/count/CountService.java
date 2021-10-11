package com.baichong.service.count;

import com.baichong.dao.entity.CountRecordDO;
import com.baichong.dao.entity.CountSplitSubjectDO;
import com.baichong.dao.entity.CountSubjectDO;
import com.baichong.dao.mapper.CountRecordMapper;
import com.baichong.dao.mapper.CountSplitSubjectMapper;
import com.baichong.dao.mapper.CountSubjectMapper;
import com.baichong.model.OutBizKey;
import com.baichong.service.idService.IdService;
import com.baichong.util.AssertUtil;
import com.baichong.util.SplitterUtils;
import com.google.common.base.Joiner;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @Author: yongzhen.z
 * @Date: 2021/9/30 11:35 上午
 */
@Service
@Log4j2
public class CountService {

    private static final Integer SPLIT_SUBJECT_COUNT = 5;

    private static final String COUNTER = "COUNTER";


    @Resource
    private CountSubjectMapper countSubjectMapper;
    @Resource
    private IdService idService;
    @Resource
    private TransactionTemplate transactionTemplate;
    @Resource
    private CountSplitSubjectMapper countSplitSubjectMapper;
    @Resource
    private CountRecordMapper countRecordMapper;

    /**
     * 查询总计数值
     *
     * @param outBizKey
     * @return
     */
    public Integer queryCountValue(OutBizKey outBizKey) {

       return queryCountValueSupplier(outBizKey).get();

//        Integer countValue = countClient.getCount(nameSpace, outBizKey.getKey());
//        if (countValue != null) {
//            return countValue;
//        }
//        countClient.putIfAbsentCount(nameSpace, outBizKey.getKey(), queryCountValueSupplier(outBizKey).get(), ttl, TimeUnit.MINUTES);
//        return countClient.getCount(nameSpace, outBizKey.getKey());
    }

    /**
     * 发布 或 更新 计数流水
     * >0 正向计数
     * <0 负向计数
     * @param outBizKey
     * @param countValue
     * @param idempotentNo
     * @return
     */
    public int publishOrUpdateCountFlow(OutBizKey outBizKey, Integer countValue, String idempotentNo) {
        log.info("开始计数 outBizKey {} countValue {} idempotentNo {}", outBizKey, countValue, idempotentNo);
        // 生成计数主体
        CountSubjectDO countSubjectDO = getOrCreateCountSubjectDO(outBizKey);
        Integer splitSubjectCount = countSubjectDO.getSplitSubjectCount();
        AssertUtil.notNull(splitSubjectCount, () -> "splitSubjectCount null");
        List<String> splitSubjectIdList = SplitterUtils.toList(countSubjectDO.getSplitSubjectId());
        //计数主体分裂
        if (splitSubjectCount - splitSubjectIdList.size() > 0) {
            createSplitSubject(splitSubjectCount - splitSubjectIdList.size(), countSubjectDO);
            countSubjectDO = getCountSubjectDO(outBizKey);
        }
        // 计数
        String singleSplitSubjectId = routeSplitSubjectId(countSubjectDO, idempotentNo);
        if (null == countRecordMapper.selectByIdempotentNo(singleSplitSubjectId, idempotentNo)) {
            Integer counterResult = transactionTemplate.execute(transactionStatus -> {
                CountRecordDO countRecordDO = new CountRecordDO();
                countRecordDO.setSubjectId(singleSplitSubjectId);
                countRecordDO.setCountRecordId(idService.genId(COUNTER).toString());
                countRecordDO.setCountValue(countValue);
                countRecordDO.setIdempotentNo(idempotentNo);
                countRecordMapper.insertSelective(countRecordDO);
                countSplitSubjectMapper.increaseCountValue(singleSplitSubjectId, countValue);
                return countValue;
            });
            // todo 查询当前计数总数，放入redis
        }
        Integer counterResult = transactionTemplate.execute(transactionStatus -> {
            //1 锁定原始记录
            CountRecordDO countRecordDO = countRecordMapper.selectByIdempotentNoForUpdate(singleSplitSubjectId, idempotentNo);
            log.info("命中 幂等 更新原始计数流水 singleSplitSubjectId {} idempotentNo {} dbValue {} toUpdateValue {}",
                    singleSplitSubjectId, idempotentNo, countRecordDO.getCountValue(), countValue);
            //2 用当前值更新
            countRecordMapper.updateCountValue(singleSplitSubjectId, idempotentNo, countValue);
            Integer incrementalValue = countValue - countRecordDO.getCountValue();
            //3 增量汇总
            countSplitSubjectMapper.increaseCountValue(singleSplitSubjectId, incrementalValue);
            //4 返回增量
            return incrementalValue;
        });
        return counterResult;
    }



//    /**
//     * 发布 或 更新 计数流水
//     * >0 正向计数
//     * <0 负向计数
//     * @param outBizKey
//     * @param countValue
//     * @param idempotentNo
//     * @return
//     */
//    public int publishOrUpdateCountFlow(OutBizKey outBizKey, Integer countValue, String idempotentNo) {
//
//        log.info("开始计数 outBizKey {} countValue {} idempotentNo {}", outBizKey, countValue, idempotentNo);
//
//        // 生成计数主体
//        CountSubjectDO countSubjectDO = getOrCreateCountSubjectDO(outBizKey);
//        Integer splitSubjectCount = countSubjectDO.getSplitSubjectCount();
//        AssertUtil.notNull(splitSubjectCount, () -> "splitSubjectCount null");
//        List<String> splitSubjectIdList = SplitterUtils.toList(countSubjectDO.getSplitSubjectId());
//
//        //计数主体分裂
//        if (splitSubjectCount - splitSubjectIdList.size() > 0) {
//            createSplitSubject(splitSubjectCount - splitSubjectIdList.size(), countSubjectDO);
//            countSubjectDO = getCountSubjectDO(outBizKey);
//        }
//        String singleSplitSubjectId = routeSplitSubjectId(countSubjectDO, idempotentNo);
//        if (null == countRecordMapper.selectByIdempotentNo(singleSplitSubjectId, idempotentNo)) {
//            Integer counterResult = transactionTemplate.execute(transactionStatus -> {
//                CountRecordDO countRecordDO = new CountRecordDO();
//                countRecordDO.setSubjectId(singleSplitSubjectId);
//                countRecordDO.setCountRecordId(idService.genId(COUNTER).toString());
//                countRecordDO.setCountValue(countValue);
//                countRecordDO.setIdempotentNo(idempotentNo);
//                countRecordMapper.insertSelective(countRecordDO);
//                countSplitSubjectMapper.increaseCountValue(singleSplitSubjectId, countValue);
//                return countValue;
//            });
//            // 查询当前计数总数，放入redis
////            if (Objects.equals(counterResult, countValue)) {
////                countClient.increase(nameSpace, outBizKey.getKey(),
////                        queryCountValueSupplier(outBizKey), ttl, TimeUnit.MINUTES,
////                        counterResult);
////                return countValue;
////            }
//        }
//        Integer counterResult = transactionTemplate.execute(transactionStatus -> {
//            //1 锁定原始记录
//            CountRecordDO countRecordDO = countRecordMapper.selectByIdempotentNoForUpdate(singleSplitSubjectId, idempotentNo);
//            log.info("命中 幂等 更新原始计数流水 singleSplitSubjectId {} idempotentNo {} dbValue {} toUpdateValue {}",
//                    singleSplitSubjectId, idempotentNo, countRecordDO.getCountValue(), countValue);
//            //2 用当前值更新
//            countRecordMapper.updateCountValue(singleSplitSubjectId, idempotentNo, countValue);
//            Integer incrementalValue = countValue - countRecordDO.getCountValue();
//            //3 增量汇总
//            countSplitSubjectMapper.increaseCountValue(singleSplitSubjectId, incrementalValue);
//            //4 返回增量
//            return incrementalValue;
//        });
//        return counterResult;
//
//    }


    private CountSubjectDO getOrCreateCountSubjectDO(OutBizKey outBizKey) {
        CountSubjectDO countSubjectDO = getCountSubjectDO(outBizKey);
        if (null == countSubjectDO) {
            countSubjectDO = new CountSubjectDO();
            countSubjectDO.setSubjectId(idService.genId(COUNTER).toString());
            countSubjectDO.setOutBizType(outBizKey.getOutBizType());
            countSubjectDO.setOutBizNo(outBizKey.getOutBizNo());
            countSubjectDO.setSplitSubjectCount(SPLIT_SUBJECT_COUNT);
            countSubjectMapper.insertSelective(countSubjectDO);
//            try {
//                countSubjectDO = new CountSubjectDO();
//                countSubjectDO.setSubjectId(idService.genId(COUNTER).toString());
//                countSubjectDO.setOutBizType(outBizKey.getOutBizType());
//                countSubjectDO.setOutBizNo(outBizKey.getOutBizNo());
//                countSubjectDO.setSplitSubjectCount(SPLIT_SUBJECT_COUNT);
//                countSubjectMapper.insertSelective(countSubjectDO);
//            } catch (DuplicateKeyException ex) {
//                return countSubjectMapper.selectByOutBizKey(outBizKey.getOutBizType(), outBizKey.getOutBizNo());
//            }
        }
        return countSubjectDO;
    }

    private CountSubjectDO getCountSubjectDO(OutBizKey outBizKey) {
        return countSubjectMapper.selectByOutBizKey(outBizKey.getOutBizType(), outBizKey.getOutBizNo());
    }


    private void createSplitSubject(int num, CountSubjectDO parentSubjectDO) {
        transactionTemplate.execute(transactionStatus -> {
            //1 锁
            CountSubjectDO countSubjectDO = countSubjectMapper.selectBySubjectIdForUpdate(parentSubjectDO.getSubjectId());
            //2 判
            if (countSubjectDO.getSplitSubjectCount() <= SplitterUtils.toList(countSubjectDO.getSplitSubjectId()).size()) {
                return true;
            }
            //3 更新
            List<String> splitSubjectIdList = SplitterUtils.toList(parentSubjectDO.getSplitSubjectId());
            for (int i = 0; i < num; i++) {
                CountSplitSubjectDO countSplitSubjectDO = new CountSplitSubjectDO();
                countSplitSubjectDO.setSubjectId(idService.genId(COUNTER).toString());
                countSplitSubjectDO.setParentSubjectId(parentSubjectDO.getSubjectId());
                countSplitSubjectMapper.insertSelective(countSplitSubjectDO);
                splitSubjectIdList.add(countSplitSubjectDO.getSubjectId());
            }
            countSubjectMapper.updateSplitSubjectId(parentSubjectDO.getSubjectId(),
                    Joiner.on(",").join(splitSubjectIdList));
            return true;
        });
    }


    /**
     * 根据idempotentNo 路由分裂计数主体id.
     *
     * @param countSubjectDO
     * @param idempotentNo
     * @return
     */
    private String routeSplitSubjectId(CountSubjectDO countSubjectDO, String idempotentNo) {

        List<String> splitSubjectIdList = SplitterUtils.toList(countSubjectDO.getSplitSubjectId());
        String hashKey = countSubjectDO.getSubjectId() + ":" + idempotentNo;
        return splitSubjectIdList.get((Math.abs(hashKey.hashCode()) % splitSubjectIdList.size()));
    }


    private Supplier<Integer> queryCountValueSupplier(OutBizKey outBizKey) {
        return () -> {
            CountSubjectDO countSubjectDO = getCountSubjectDO(outBizKey);
            if (countSubjectDO == null) {
                return 0;
            }
            List<String> splitSubjectIdList = SplitterUtils.toList(countSubjectDO.getSplitSubjectId());
            return splitSubjectIdList.stream().map(subjectId -> countSplitSubjectMapper.selectBySubjectId(subjectId))
                    .filter(Objects::nonNull)
                    .map(CountSplitSubjectDO::getCountValue)
                    .filter(Objects::nonNull)
                    .mapToInt(item -> item).sum();
        };
    }


}
