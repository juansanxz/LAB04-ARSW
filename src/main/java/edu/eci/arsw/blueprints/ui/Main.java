package edu.eci.arsw.blueprints.ui;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String a[]) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = ac.getBean(BlueprintsServices.class);

        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);

        Point[] pts2=new Point[]{new Point(21, 30),new Point(10, 10)};
        Blueprint bp2=new Blueprint("antonio", "firstpaint",pts2);

        Point[] pts3=new Point[]{new Point(23, 43),new Point(10, 10)};
        Blueprint bp3=new Blueprint("john", "anotherpaint",pts3);

        System.out.println("----------Añadiendo planos----------");
        try {
            bps.addNewBlueprint(bp);
            bps.addNewBlueprint(bp2);
            bps.addNewBlueprint(bp3);

        } catch (BlueprintPersistenceException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("----------Obteniendo todos los planos----------");
        System.out.println(bps.getAllBlueprints());

        System.out.println("----------Obteniendo un plano por nombre de autor y nombre del plano----------");
        try {
            System.out.println(bps.getBlueprint("john","anotherpaint"));
        } catch (BlueprintNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("----------Obteniendo todos los planos de un autor----------");
        try {
            System.out.println(bps.getBlueprintsByAuthor("antonio"));
        } catch (BlueprintNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("----------Creando plano nuevo para ser filtrado----------");

        Point pt = new Point(0, 0);
        Point pt1 = new Point(10, 10);
        Point pt2 = new Point(12, 12);
        Point pt3 = new Point(14, 14);
        Point pt4 = new Point(21, 32);
        Point pt5 = new Point(213, 213);
        Point pt6 = new Point(21, 12);

        Blueprint bp6=new Blueprint("andres", "thepaint");
        bp6.addPoint(pt4);
        bp6.addPoint(pt5);
        bp6.addPoint(pt6);
        bp6.addPoint(pt6);

        System.out.println("----------Añadiendo plano----------");
        try {
            bps.addNewBlueprint(bp6);
        } catch (BlueprintPersistenceException e) {
            throw new RuntimeException(e);
        }

        System.out.println("----------Obteniendo  planos sin filtrar de un autor----------");
        try {
            System.out.println(bps.getBlueprintsByAuthor("andres"));
        } catch (BlueprintNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("----------Filtrando plano----------");
        bps.getBlueprintFiltered(bp6);

        System.out.println("----------Obteniendo  planos filtrados de un autor----------");
        try {
            System.out.println(bps.getBlueprintsByAuthor("andres"));
        } catch (BlueprintNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}
