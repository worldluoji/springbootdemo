package com.mongo.simplemongo;

import com.mongo.simplemongo.convert.MoneyReadConvert;
import com.mongo.simplemongo.model.Coffee;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Slf4j
@SpringBootApplication
public class SimpleMongoApplication implements ApplicationRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SimpleMongoApplication.class, args);
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(new MoneyReadConvert()));
    }

    private void testMongoCrud() throws InterruptedException {
        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .createTime(new Date())
                .updateTime(new Date()).build();
        Coffee saved = mongoTemplate.save(espresso);
        log.info("Coffee {}", saved);

        List<Coffee> list = mongoTemplate.find(
                query(where("name").is("espresso")), Coffee.class);
        log.info("Find {} Coffee", list.size());

        Thread.sleep(1000); // 为了看更新时间
        UpdateResult result = mongoTemplate.updateFirst(query(where("name").is("espresso")),
                new Update().set("price", Money.ofMajor(CurrencyUnit.of("CNY"), 30))
                        .currentDate("updateTime"),
                Coffee.class);
        log.info("Update Result: {}", result.getModifiedCount());
        Coffee updateOne = mongoTemplate.findById(saved.getId(), Coffee.class);
        log.info("Update Result: {}", updateOne);

        mongoTemplate.remove(updateOne);
    }

    private void addCoffee(String coffeeName, double price) {
        Coffee natty = Coffee.builder()
                .name(coffeeName)
                .price(Money.of(CurrencyUnit.of("CNY"), price))
                .createTime(new Date())
                .updateTime(new Date()).build();
        Coffee saved = mongoTemplate.save(natty);
        log.info("Coffee {}", saved);
    }

    private void batchAddCoffee() {
        addCoffee("natty", 30.0);
        addCoffee("mocha", 25.5);
        addCoffee("flatWhite", 33.0);
        addCoffee("breve", 40);
    }


    /* 将价格大于30的咖啡的名字查出，名字按照字典序降序排列
    * db.coffee.aggregate([{$project:{'name':'$name','price':'$price.money.amount'}},
    * {$match:{'price':{$gt:"30.00"}}},{$group:{'_id':'$name'}},{$sort:{'_id':-1}}])
    * */
    private void testAggregateDemo1() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("price.money.amount").gt("30.00")),
                Aggregation.group("name"),
                Aggregation.sort(Sort.Direction.DESC, "name")
        );
        AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(aggregation, "coffee", Map.class);
        if (aggregationResults != null) {
            List<Map> mappedResults = aggregationResults.getMappedResults();
            if (!CollectionUtils.isEmpty(mappedResults)) {
                List<String> coffeNames = mappedResults.stream()
                        .map((Map map)->{
                            return (String)map.get("_id");
                        }).collect(toList());
                log.info("The matched result is {}", coffeNames);
            }
        }

    }

    private void showNameAndPrice(AggregationResults aggregationResults) {
        if (aggregationResults != null) {
            List<Map> mappedResults = aggregationResults.getMappedResults();
            if (!mappedResults.isEmpty()) {
                log.info("The result is {}，{}",mappedResults.get(0).get("name"), mappedResults.get(0).get("money"));
            }
        }
    }

    /**
    * db.coffee.aggregate([{$match:{'price.money.amount':{$lt:"30.00"}}},{
     * $project:{'_id':0,'name':'$name','price':'$price.money.amount'}}])
     * { "name" : "mocha", "price" : "25.50" }
    * */
    private void testAggregateDemo2() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("price.money.amount").lt("30.00")),
                Aggregation.project("name","price.money.amount")
        );
        AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(aggregation, "coffee", Map.class);
        showNameAndPrice(aggregationResults);
    }

    /**
    * and和or查询
    * */
    private void testAggregationdemo3() {
        //Example<Coffee> example = Example.of(Coffee.builder().name("espresso").build());alike
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("price.money.amount").is("30.00").and("name").is("espresso")),
                Aggregation.project("name","price.money.amount")
        );
        AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(aggregation, "coffee", Map.class);
        showNameAndPrice(aggregationResults);
    }

    /**
     * and和or查询
     * */
    private void testAggregationdemo4() {
        //Example<Coffee> example = Example.of(Coffee.builder().name("espresso").build());alike
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("price.money.amount").is("30.00").orOperator(Criteria.where("name").is("natty"))),
                Aggregation.project("name","price.money.amount")
        );
        AggregationResults<Map> aggregationResults = mongoTemplate.aggregate(aggregation, "coffee", Map.class);
        showNameAndPrice(aggregationResults);
    }

    /**
    * 使用Example和ExampleMathr查询
    * */
    private void testAggregationdemo5() {
        //创建匹配器，即如何使用查询条件
        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains()) //采用“包含匹配”的方式查询
                .withIgnorePaths("createTime", "updateTime");  //忽略属性，不参与查询

        Example<Coffee> example = Example.of(Coffee.builder().price(Money.of(CurrencyUnit.of("CNY"),30)).build(), matcher);
        Query query = new Query();
        query.addCriteria(Criteria.byExample(example));
        List<Coffee> coffees = mongoTemplate.find(query, Coffee.class);
        for (Coffee coffee:coffees) {
            if (coffee != null) {
                log.info("The matched coffee is {}", coffee.getName());
            }
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        //testAggregateDemo1();
        //testAggregateDemo2();
        //testAggregationdemo3();
        //testAggregationdemo4();
        testAggregationdemo5();
    }
}
