package com.boot.rest.CreditCardManagement.service;

import com.boot.rest.CreditCardManagement.dao.TranscationRepository;
import com.boot.rest.CreditCardManagement.entity.CategoryTransactions;
import com.boot.rest.CreditCardManagement.entity.Customer;
import com.boot.rest.CreditCardManagement.entity.StateTransactions;
import com.boot.rest.CreditCardManagement.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class TransactionService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private TranscationRepository transcationRepository;

    public List<Transaction> getTransaction(String gender, String category, String merchant, String city,
                                            String state, String job, int amt){
        Query query = new Query();
        ArrayList<Criteria> list = new ArrayList<Criteria>();
        if(!(gender.equals(" "))){
            list.add(Criteria.where("gender").is(gender));
        }
        if(!(category.equals(" "))){
            list.add(Criteria.where("category").regex(".*?" + category +".*").regex(category,"i"));
        }
        if(!(merchant.equals(" "))){
            list.add(Criteria.where("merchant").regex(".*?" + merchant +".*").regex(merchant,"i"));
        }
        if(!(city.equals(" "))){
            list.add(Criteria.where("city").regex(".*?" + city +".*").regex(city,"i"));
        }
        if(!(state.equals(" "))){
            list.add(Criteria.where("state").regex(".*?" + state +".*").regex(state,"i"));
        }
        if(!(job.equals(" "))){
            list.add(Criteria.where("Job").regex(".*?" + job +".*").regex(job,"i"));
        }
        if(!(amt == -1)){
            if(amt == 1){
                list.add(Criteria.where("amt").lte(50));
            }else if(amt == 2){
                list.add(Criteria.where("amt").gte(50));
            }
        }
        Criteria[] arr = new Criteria[list.size()];
        if(arr.length > 0){
            list.toArray(arr);
            Criteria criteria = new Criteria().andOperator(arr);
            query.addCriteria(criteria);
        }
        List<Transaction> transactions = mongoTemplate.find(query, Transaction.class, "transaction");
        return transactions;

    }

    public List<StateTransactions> getTransactionsForCountry() {

        GroupOperation groupByCountrySumSales = group("state").sum("amt").as("total_transactions");
        MatchOperation allStates = match(new Criteria("state").exists(true));
        ProjectionOperation includes = project("total_transactions").and("state").previousOperation();
        SortOperation sortByTransactionsDesc = sort(Sort.by(Sort.Direction.DESC,"total_transactions"));
        Aggregation aggregation = newAggregation(allStates,groupByCountrySumSales,sortByTransactionsDesc,includes);
        AggregationResults<StateTransactions> groupResults = mongoTemplate.aggregate(aggregation, "transaction", StateTransactions.class);
        List<StateTransactions> result = groupResults.getMappedResults();
        return result;
    }

    public List<CategoryTransactions> getTransactionsForCategory(String state) {

        GroupOperation groupByCategorySumSales = group("category").sum("amt").as("total_transactions");
        MatchOperation allStates = match(new Criteria("state").is(state));
        ProjectionOperation includes = project("total_transactions").and("category").previousOperation();
        SortOperation sortByTransactionsDesc = sort(Sort.by(Sort.Direction.DESC,"total_transactions"));
        Aggregation aggregation = newAggregation(allStates,groupByCategorySumSales,sortByTransactionsDesc,includes);
        AggregationResults<CategoryTransactions> groupResults = mongoTemplate.aggregate(aggregation, "transaction", CategoryTransactions.class);
        List<CategoryTransactions> result = groupResults.getMappedResults();
        return result;
    }

    public List<Transaction> getAllTransaction(){
        return transcationRepository.findAll();
    }


//    public void deleteById(long id){
//        if(transcationRepository.findById(id).isEmpty()){
//            return ;
//        }
//        else{
//
//            transcationRepository.deleteById(id);
//            System.out.println("delete" +id);
//        }
//    }

}
