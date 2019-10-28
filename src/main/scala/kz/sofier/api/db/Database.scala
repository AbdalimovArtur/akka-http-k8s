package kz.sofier.api.db

import cats.effect.IO
import kz.sofier.api.utils.config.DatabaseConfig
import org.flywaydb.core.Flyway

object Database {

  def initialize(config: DatabaseConfig): IO[Unit] = IO {
    println(config)
    Flyway
      .configure()
      .schemas(config.schema)
      .dataSource(config.url, config.user, config.password)
      .load()
      .migrate()
    ()
  }
}
