package edu.eci.arsw.blueprints.ui;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bs = ctx.getBean(BlueprintsServices.class);

        // Crear y registrar planos
        Blueprint bp1 = new Blueprint("Juan", "Casa", new Point[]{new Point(0,0), new Point(1,1), new Point(1,1), new Point(2,2)});
        Blueprint bp2 = new Blueprint("Juan", "Oficina", new Point[]{new Point(0,0), new Point(2,2), new Point(4,4), new Point(4,4)});
        bs.addNewBlueprint(bp1);
        bs.addNewBlueprint(bp2);

        // Consultar y mostrar plano filtrado
        Blueprint result = bs.getBlueprint("Juan", "Casa");
        System.out.println("Plano filtrado: " + result.getPoints());
    }
}
