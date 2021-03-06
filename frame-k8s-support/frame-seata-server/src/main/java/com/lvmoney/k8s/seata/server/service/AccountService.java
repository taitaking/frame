package com.lvmoney.k8s.seata.server.service;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public interface AccountService {
    /**
     * 更新用户
     *
     * @param userId: 用户id
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:17
     */
    int updateAccount(String userId);
}
