package mc.Serviceimpl;

import mc.Entity.NodeInfo;
import mc.Repository.NodeRepository;
import mc.Repository.ServiceRepository;
import mc.Service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulerServiceImpl implements SchedulerService {
    @Autowired
    WebApplicationContext applicationContext;
    @Override
    public String Schedule(String serviceName) {
        NodeRepository nodeRepository=applicationContext.getBean(NodeRepository.class);
        ServiceRepository serviceRepository=applicationContext.getBean(ServiceRepository.class);
        List<NodeInfo> nodeInfos=nodeRepository.findAll();
        List<NodeInfo> filtered=new ArrayList<>();
        for (NodeInfo nodeInfo : nodeInfos) {
            if (nodeInfo.getApplicable()) {
                filtered.add(nodeInfo);
            }
        }
        return "";


    }
}
