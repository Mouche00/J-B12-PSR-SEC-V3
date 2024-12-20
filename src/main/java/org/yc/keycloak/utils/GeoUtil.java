package org.yc.keycloak.utils;

import org.yc.keycloak.utils.records.Cartesian;
import org.yc.keycloak.utils.records.Coordinates;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GeoUtil {
    private static final double EARTH_RADIUS_KM = 6371.0;
    private static final double STEP = 0.0001;

    public double haversine(Coordinates coordinates1, Coordinates coordinates2) {
        double dLat = Math.toRadians(coordinates2.getLatitude() - coordinates1.getLatitude());
        double dLon = Math.toRadians(coordinates2.getLongitude() - coordinates1.getLongitude());

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(coordinates1.getLatitude())) * Math.cos(Math.toRadians(coordinates2.getLatitude()))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS_KM * c;
    }

    public List<Double> calculateDistances(List<Coordinates> finishPoints, Coordinates startPoint) {
        return finishPoints.stream().map(p -> haversine(startPoint, p)).toList();
    }

    public double calculateDistancesAverage(List<Double> distances) {
        return distances.stream().mapToDouble(d -> d).average().orElse(0.0);
    }

    public boolean checkEquidistantDistances(List<Double> distances, double averageDistance, double tolerance) {
        return distances.stream().allMatch(d -> Math.abs(d - averageDistance) <= tolerance);
    }

    public boolean isEquidistant(List<Coordinates> finishPoints, Coordinates startPoint, double tolerance) {
        List<Double> distances = calculateDistances(finishPoints, startPoint);
        double averageDistance = calculateDistancesAverage(distances);
        return checkEquidistantDistances(distances, averageDistance, tolerance);
    }


    public Coordinates getEquidistantPoint(List<Coordinates> finishCoordinates) {

        List<Cartesian> cartesians = finishCoordinates.stream().map(this::toCartesian).toList();

        double x = cartesians.stream().mapToDouble(Cartesian::x).average().orElse(0.0);
        double y = cartesians.stream().mapToDouble(Cartesian::y).average().orElse(0.0);
        double z = cartesians.stream().mapToDouble(Cartesian::z).average().orElse(0.0);

        // this code block projects the cartesian point back to the earth surface
        double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        double scale = EARTH_RADIUS_KM / length;
        Cartesian cartesian = new Cartesian(x * scale, y * scale, z * scale);

        return toCoordinates(cartesian);
    }

    private Cartesian toCartesian(Coordinates coordinates) {
        double latRad = Math.toRadians(coordinates.getLatitude());
        double lonRad = Math.toRadians(coordinates.getLongitude());

        double x = EARTH_RADIUS_KM * Math.cos(latRad) * Math.cos(lonRad);
        double y = EARTH_RADIUS_KM * Math.cos(latRad) * Math.sin(lonRad);
        double z = EARTH_RADIUS_KM * Math.sin(latRad);

        return new Cartesian(x, y, z);
    }

    private Coordinates toCoordinates(Cartesian cartesian) {
        double lon = Math.atan2(cartesian.y(), cartesian.x());
        double hyp = Math.sqrt(Math.pow(cartesian.x(), 2) + Math.pow(cartesian.y(), 2));
        double lat = Math.atan2(cartesian.z(), hyp);
        return new Coordinates(Math.toDegrees(lat), Math.toDegrees(lon));
    }

//    public Coordinates generateClosestEquidistant(List<Coordinates> finishPoints, Coordinates startPoint, double tolerance) {
//        Coordinates candidate = startPoint;
//
//        for (int i = 0; i < 1000; i++) { // Optimization loop
//            List<Double> distances = calculateDistances(finishPoints, startPoint);
//            double averageDistance = calculateDistancesAverage(distances);
//
//            if (checkEquidistantDistances(distances, averageDistance, tolerance)) {
//                break;
//            }
//
//            double adjustmentLat = distances.stream().mapToDouble(d -> d - meanDistance).average().orElse(0.0);
//            double adjustmentLon = distances.stream().mapToDouble(d -> d - meanDistance).average().orElse(0.0);
//
//            candidate = new Coordinates(
//                    candidate.latitude() + adjustmentLat * STEP,
//                    candidate.longitude() + adjustmentLon * STEP
//            );
//        }
//
//        return candidate;
//    }
}
