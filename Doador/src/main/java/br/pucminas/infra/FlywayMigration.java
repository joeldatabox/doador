package br.pucminas.infra;

import org.flywaydb.core.Flyway;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.stereotype.Component;

/**
 * Created by Joel Rodrigues on 04/10/2016.
 */
@Component
public class FlywayMigration implements FlywayMigrationStrategy {
    @Override
    public void migrate(Flyway flyway) {
        flyway.setBaselineOnMigrate(true);
        flyway.migrate();
    }
}
