package com.bank.example.dao;

import com.bank.example.model.Account;
import com.bank.example.model.CashBackCategory;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityGraph;
import javax.persistence.Subgraph;
import java.util.List;

@Repository
public class CashBackCategoryDaoImpl extends AbstractDao<Long, CashBackCategory> implements CashBackCategoryDao {


    public List<CashBackCategory> getCashBackCategoryJoinFetch() {

        EntityGraph<CashBackCategory> entityGraph = entityManager.createEntityGraph(CashBackCategory.class);
        entityGraph.addAttributeNodes("cashBackCompanies");
        Subgraph<Account> subgraph = entityGraph.addSubgraph("uploader");
        subgraph.addAttributeNodes("department");

        return entityManager.createQuery("SELECT o FROM CashBackCategory o",
                        CashBackCategory.class)
                .setHint("javax.persistence.loadgraph", entityGraph)
                .getResultList();

    }

}
