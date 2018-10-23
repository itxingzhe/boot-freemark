package cn.wyb.personal.service.express.impl;

import org.springframework.beans.factory.annotation.Value;

import cn.wyb.personal.service.express.ExpressClientService;

/**
 * ExpressClientServiceImpl: 快递服务业务实现
 *
 * @author wangyibin
 * @date 2018/10/11 15:04
 * @see
 */
public class ExpressClientServiceImpl implements ExpressClientService {

    @Value("${express_waybill_url:http://api.kuaidi100.com/eorderapi.do?method=getElecOrder}")
    private String expressWaybillUrl;

    @Value("${express_query_url:https://poll.kuaidi100.com/poll/query.do}")
    private String expressQueryUrl;

    @Value("${express_key:tMoeWqfy1314}")
    private String expressKey;

    @Value("${express_customer:AF27DD3927081E9AB5806BA93617261E}")
    private String expressCustomer;

    @Value("${express_secret:c974105c6cd947368328248ae3a95897}")
    private String expressSecret;

    @Value("${express_sms_id:755fd6d33d9c42309256be03a411d011}")
    private String expressSmsId;

}
