package com.application.repository;

import com.application.model.indicators.IndicatorPK;
import com.application.model.indicators.IndicatorsGrowth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IndicatorsGrowthRepository extends JpaRepository<IndicatorsGrowth, IndicatorPK> {

    /***
     * Queries Debt Indicators by Ticker and Range of Days
     *
     * HQL does not support Date subtraction natively
     * So use 'make_interval' and 'date_subtract', which are native to Postgres
     * 'make_interval' uses last field as Days to create an 'interval of days' that Postgres/HQL accept
     * 'date_subtract' should return a date, but HQL considers it an Object
     * So add a CAST AS DATE to it all
     *
     * @param ticketName
     * @param dayRange
     * @return
     */
    @Query("""
            SELECT growth
            FROM IndicatorsGrowth growth
            WHERE growth.indicatorPK.ticketName = :ticketName
            AND growth.indicatorPK.storageDate >= CAST(date_subtract(CURRENT_DATE, make_interval(0,0,0, :dayRange)) AS DATE)
            ORDER BY growth.indicatorPK.storageDate ASC
            """)
    public List<IndicatorsGrowth> findAllByTicketAndRange(@Param("ticketName") String ticketName, @Param("dayRange") int dayRange);

}
