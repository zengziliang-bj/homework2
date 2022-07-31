package com.gientech.practice.filter;

import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;


@Slf4j
public class SqlCheckFilter extends FilterEventAdapter {
	
    @SneakyThrows
    @Override
    public void statementExecuteBefore(StatementProxy statement, String sql){
        log.info(sql);

        List<SQLStatement> statements = SQLUtils.parseStatements(sql, JdbcConstants.H2);
        if (statements.size()>1){
            throw new SQLException();
        }
        SQLStatement sqlStatement = statements.get(0);
        MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
        sqlStatement.accept(visitor);
        for (TableStat.Condition condition:visitor.getConditions()) {
            if(!condition.getOperator().equalsIgnoreCase("IN")) continue;
            Object[] objects = condition.getValues().toArray();
            if(objects.length > 5){
                throw new SQLException();
            }
        }
    }
}
