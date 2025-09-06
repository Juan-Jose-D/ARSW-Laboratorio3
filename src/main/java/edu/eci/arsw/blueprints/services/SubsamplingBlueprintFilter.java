package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("subsamplingFilter")
public class SubsamplingBlueprintFilter implements BlueprintFilter {
    @Override
    public List<Point> filter(List<Point> points) {
        List<Point> filtered = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            if (i % 2 == 0) {
                filtered.add(points.get(i));
            }
        }
        return filtered;
    }

    @Override
    public Blueprint filterBlueprint(Blueprint bp) {
        List<Point> originalPoints = bp.getPoints();
        List<Point> filteredPoints = new ArrayList<>();

        for (int i = 0; i < originalPoints.size(); i++) {
            if (i % 2 == 0) {
                filteredPoints.add(originalPoints.get(i));
            }
        }

        bp.setPoints(filteredPoints);
        return bp;
    }

    @Override
    public Set<Blueprint> filterBlueprints(Set<Blueprint> blueprints) {
        Set<Blueprint> filteredBlueprints = new HashSet<>();
        for (Blueprint bp : blueprints) {
            filteredBlueprints.add(filterBlueprint(bp));
        }
        return filteredBlueprints;
    }
}
