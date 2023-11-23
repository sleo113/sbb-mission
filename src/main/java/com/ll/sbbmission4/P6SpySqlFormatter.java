package com.ll.sbbmission4;

import com.p6spy.engine.logging.Category;
import com.p6spy.engine.spy.P6SpyOptions;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import jakarta.annotation.PostConstruct;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class P6SpySqlFormatter implements MessageFormattingStrategy {

    @PostConstruct
    public void setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(this.getClass().getName());
    }

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {

        // SESSION 관련 쿼리 출력 하지 않기
        // TODO yml로 변수들 옮기기
        if (isIgnoredTable(sql, "SPRING_SESSION") || isIgnoredTable(sql, "SPRING_SESSION_ATTRIBUTES")) {
            return "session sql";
        }

        sql = formatSql(category, sql);
        return String.format("[%s] | %d ms | %s", category, elapsed, highlight(formatSql(category, sql)));
    }

    private String formatSql(String category, String sql) {
        if (sql != null && !sql.trim().isEmpty() && Category.STATEMENT.getName().equals(category)) {
            String trimmedSQL = sql.trim().toLowerCase(Locale.ROOT);
            if (trimmedSQL.startsWith("create") || trimmedSQL.startsWith("alter") || trimmedSQL.startsWith("comment")) {
                sql = FormatStyle.DDL.getFormatter().format(sql);
            } else {
                sql = FormatStyle.BASIC.getFormatter().format(sql);
            }
            return sql;
        }
        return sql;
    }

    private String highlight(String sql) {
        return FormatStyle.HIGHLIGHT.getFormatter().format(sql);
    }

    private boolean isIgnoredTable(String sql, String tableName) {
        if (sql != null) {
            return sql.contains(tableName);
        }
        return false;
    }
}