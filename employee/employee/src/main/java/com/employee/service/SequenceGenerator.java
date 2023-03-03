package com.employee.service;

import com.employee.model.DbSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class SequenceGenerator {

    @Autowired
    private MongoOperations operations;

    public int getSequence(String seqName){
        //get sequence no
        Query query = new Query(Criteria.where("id").is(seqName));
        //update seq no
        Update update = new Update().inc("seq", 1);
        //modify seq collection
        DbSequence counter = operations.
                findAndModify(query, update, options().returnNew(true).upsert(true),
                        DbSequence.class);
        return !Objects.isNull(counter) ? counter.getSeqNo() : 1 ;
    }
}
