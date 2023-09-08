package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.services.RedundanciesBlueprintFilter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class RedundanciesBlueprintFilterTest {

    @Test
    public void getExistingBpByAuthorAndBpNamesTest() throws BlueprintNotFoundException {
        RedundanciesBlueprintFilter rbpf = new RedundanciesBlueprintFilter();

        Point pt = new Point(0, 0);
        Point pt1 = new Point(10, 10);
        Point pt2 = new Point(12, 12);

        Blueprint bp=new Blueprint("john", "thepaint");
        bp.addPoint(pt);
        bp.addPoint(pt);
        bp.addPoint(pt1);

        Blueprint bp1=new Blueprint("john", "thepaint");
        bp1.addPoint(pt);
        bp1.addPoint(pt1);

        assertEquals("Getting a filtered  blueprint returned a different blueprint.", bp1, rbpf.getFilteredBlueprint(bp));

        Blueprint bp2=new Blueprint("john", "thepaint");
        bp2.addPoint(pt);
        bp2.addPoint(pt);
        bp2.addPoint(pt1);
        bp2.addPoint(pt1);
        bp2.addPoint(pt1);
        bp2.addPoint(pt1);

        assertEquals("Getting a filtered  blueprint returned a different blueprint.", bp1, rbpf.getFilteredBlueprint(bp2));

        Blueprint bp3=new Blueprint("john", "thepaint");
        bp3.addPoint(pt);
        bp3.addPoint(pt);
        bp3.addPoint(pt1);
        bp3.addPoint(pt1);
        bp3.addPoint(pt2);
        bp3.addPoint(pt2);

        Blueprint bp4=new Blueprint("john", "thepaint");
        bp4.addPoint(pt);
        bp4.addPoint(pt1);
        bp4.addPoint(pt2);

        assertEquals("Getting a filtered  blueprint returned a different blueprint.", bp4, rbpf.getFilteredBlueprint(bp3));

    }



}
