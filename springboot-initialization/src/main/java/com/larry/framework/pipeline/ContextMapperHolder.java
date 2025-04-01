//package com.larry.framework.pipeline;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Component
//public class ContextMapperHolder {
//
//    @Autowired
//    private List<ContextMapper> mappers;
//
//    private Map<ProcessType, ContextMapper> mapperMap = new HashMap();
//
//    @PostConstruct
//    private void init() {
//        mapperMap = mappers.stream().collect(Collectors.toMap(ContextMapper::getProcessType, mapper -> mapper));
//    }
//
//    public ContextMapper getMapper(ProcessType processType) {
//        ContextMapper mapper = mapperMap.get(processType);
//        if(mapper==null){
//            throw new IllegalStateException("Mapper not found for type " + processType.name());
//        }
//        return mapper;
//    }
//}
