package ir.mahdi.startup.startup.repository.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PrintRequestCustomRepImpl implements PrintRequestCustomRep, BeanPostProcessor {
    Logger logger = LoggerFactory.getLogger(PrintRequestCustomRepImpl.class);

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public List<String> getAllIpAddressByBranchCode(String branchCode) {
        Query query = entityManager.createQuery("SELECT p.ipAddress FROM PrintRequest p WHERE p.branchCode =:branchCode");
        query.setParameter("branchCode", branchCode);
        return query.getResultList();
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.debug("this is PrintRequestCustomRepImpl");
        return bean;
    }
}
