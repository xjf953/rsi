package com.shawn.votesystem.service;

import com.shawn.votesystem.dto.ResultMsg;

public interface TelethonService {
    ResultMsg getPinMessage(String tgName);

    ResultMsg getMembers(String tgName);

    ResultMsg getPinFromRedis(String id);
}
