package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.RedundanciesBlueprintFilter;
import edu.eci.arsw.blueprints.services.SubsamplingBlueprintFilter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubsamplingBlueprintFilterTest {

    @Test
    public void getExistingBpByAuthorAndBpNamesTest() throws BlueprintPersistenceException, BlueprintNotFoundException {
        SubsamplingBlueprintFilter sbpf = new SubsamplingBlueprintFilter();

        Point pt = new Point(0, 0);
        Point pt1 = new Point(10, 10);
        Point pt2 = new Point(12, 12);
        Point pt3 = new Point(14, 14);
        Point pt4 = new Point(21, 32);
        Point pt5 = new Point(213, 213);
        Point pt6 = new Point(21, 12);


        Blueprint bp=new Blueprint("john", "thepaint");
        bp.addPoint(pt);

        Blueprint bp1=new Blueprint("john", "thepaint");
        bp1.addPoint(pt);

        assertEquals("Getting a filtered  blueprint returned a different blueprint.", bp1, sbpf.getFilteredBlueprint(bp));

        Blueprint bp2=new Blueprint("john", "thepaint");
        bp2.addPoint(pt);
        bp2.addPoint(pt1);

        Blueprint bp3=new Blueprint("john", "thepaint");
        bp3.addPoint(pt1);

        assertEquals("Getting a filtered  blueprint returned a different blueprint.", bp3, sbpf.getFilteredBlueprint(bp2));

        Blueprint bp4=new Blueprint("john", "thepaint");
        bp4.addPoint(pt);
        bp4.addPoint(pt1);
        bp4.addPoint(pt2);

        assertEquals("Getting a filtered  blueprint returned a different blueprint.", bp3, sbpf.getFilteredBlueprint(bp4));

        Blueprint bp5=new Blueprint("john", "thepaint");
        bp5.addPoint(pt);
        bp5.addPoint(pt1);
        bp5.addPoint(pt2);
        bp5.addPoint(pt3);

        bp3.addPoint(pt3);

        assertEquals("Getting a filtered  blueprint returned a different blueprint.", bp3, sbpf.getFilteredBlueprint(bp5));

        Blueprint bp6=new Blueprint("john", "thepaint");
        bp6.addPoint(pt);
        bp6.addPoint(pt1);
        bp6.addPoint(pt2);
        bp6.addPoint(pt3);
        bp6.addPoint(pt4);
        bp6.addPoint(pt5);
        bp6.addPoint(pt6);

        bp3.addPoint(pt5);

        assertEquals("Getting a filtered  blueprint returned a different blueprint.", bp3, sbpf.getFilteredBlueprint(bp6));

    }

}
