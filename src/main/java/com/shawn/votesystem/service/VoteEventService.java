package com.shawn.votesystem.service;

import com.shawn.votesystem.dto.ResultMsg;
import com.shawn.votesystem.dto.VoteEventDTO;

public interface VoteEventService {

    ResultMsg save(VoteEventDTO voteEventDTO);

    void processVote(VoteEventDTO eventDTO);
}
