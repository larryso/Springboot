package com.larry.service;

import com.larry.dto.InboundLinkDTO;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InboundLinkServiceImpl implements InboundLinkService {
    @Override
    public InboundLinkDTO create(InboundLinkDTO inboundLinkDTO) {
        inboundLinkDTO.getCollectionRdrId().intValue();
        inboundLinkDTO.setLinkId(UUID.randomUUID().toString());
        return inboundLinkDTO;
    }
}
