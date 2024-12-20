//package org.jb10pigeonskyracesecurity.bootstrap;
//
//import lombok.RequiredArgsConstructor;
//import org.jb10pigeonskyracesecurity.dtos.CompetitionDTO;
//import org.jb10pigeonskyracesecurity.dtos.RaceDTO;
//import org.jb10pigeonskyracesecurity.models.Breeder;
//import org.jb10pigeonskyracesecurity.services.BreederAuthService;
//import org.jb10pigeonskyracesecurity.services.CompetitionService;
//import org.jb10pigeonskyracesecurity.services.RaceService;
//import org.jb10pigeonskyracesecurity.utils.records.Coordinates;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class BootstrapData implements CommandLineRunner {
//
//    private final CompetitionService competitionService;
//    private final RaceService raceService;
//    private final BreederAuthService breederAuthService;
//
//    @Override
//    public void run(String... args) throws Exception {
//        loadCompetitionAndRaces();
//        loadBreeders();
//    }
//
//    private void loadCompetitionAndRaces() {
//        CompetitionDTO competitionDTO = CompetitionDTO.builder()
//                .name("National Championship")
//                .closedAt(LocalDateTime.now().plusMonths(1)) // Competition closes in 1 month
//                .build();
//
//        CompetitionDTO savedCompetition = competitionService.save(competitionDTO);
//        System.out.println("Competition saved: " + savedCompetition.id());
//
//
//        List<RaceDTO> raceDTOs = new ArrayList<>();
//        for (int i = 1; i <= 3; i++) {
//            RaceDTO raceDTO = RaceDTO.builder()
//                    .name("Race " + i)
//                    .startCoordinates(new Coordinates(40.7128, -74.0060))
//                    .startDate(LocalDateTime.now().plusDays(i))
//                    .targetDistance(500 + (i * 50))
//                    .closedAt(LocalDateTime.now().plusDays(i + 10))
//                    .competition(savedCompetition)
//                    .build();
//
//            RaceDTO savedRace = raceService.save(raceDTO.withCompetitionId(savedCompetition.id()));
//            raceDTOs.add(savedRace);
//            System.out.println("Race saved: " + savedRace);
//        }
//    }
//
//    private void loadBreeders() {
//        for (int i = 1; i <= 11; i++) {
//            Breeder breeder = new Breeder();
//            breeder.setUsername("breeder" + i);
//            breeder.setPassword("password" + i);
//            breeder.setLoftName("Loft " + i);
//            breeder.setLoftCoordinates(new Coordinates(34.0522, -118.2437)); // Example loft coordinates
//            breeder.setFinalScore(0.0);
//
//            String message = breederAuthService.register(breeder);
//            System.out.println(message + ": " + breeder.getUsername());
//        }
//    }
//}