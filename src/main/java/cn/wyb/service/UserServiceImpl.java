package cn.wyb.service;

import cn.wyb.dao.UserPOMapper;
import cn.wyb.model.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create Time: 2018年04月26日 14:15
 * C@author wyb
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserPOMapper userPOMapper;

    @Override
    public UserPO getUser(Integer id) {
        return userPOMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer save(UserPO user) {

        return userPOMapper.insertSelective(user);
    }

    @Override
    public Integer update(UserPO user) {
        return userPOMapper.updateByPrimaryKeySelective(user);
    }
}
