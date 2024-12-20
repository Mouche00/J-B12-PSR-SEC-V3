//package org.jb10pigeonskyracesecurity.services.implementations;
//
//import org.jb10pigeonskyracesecurity.dtos.RankingDTO;
//import org.jb10pigeonskyracesecurity.services.RankingService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.util.ResourceUtils;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class RankingServiceImplTest {
//
//    @Autowired
//  private RankingService rankingService;
//
//    @Test
//    void convertCSV() throws FileNotFoundException {
//        File file = ResourceUtils.getFile("classpath:csvData/ranking.csv");
////        List<Ranking> rankingList = rankingService.convertCSV(file);
//
//        List<RankingDTO> rankingList = rankingService.saveAll(file);
//                System.out.println(rankingList.size());
//        for (RankingDTO ranking : rankingList) {
//            System.out.println(ranking);
//        }
//        assertTrue(rankingList.size() > 0);
//    }
//}