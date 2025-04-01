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
//public class ProcessHolder {
//    @Autowired
//    private List<Process> processList;
//    private Map<ProcessType, Process> processMap = new HashMap<>();
//    @PostConstruct
//    private void init(){
//        processMap = processList.stream().collect(Collectors.toMap(Process::getProcessType, process -> process));
//    }
//    public Process getProcess(ProcessType processType){
//        Process process = processMap.get(processType);
//        if(process == null){
//            throw new IllegalStateException("Process not found for Type: "+ processType);
//        }
//        return process;
//    }
//}
