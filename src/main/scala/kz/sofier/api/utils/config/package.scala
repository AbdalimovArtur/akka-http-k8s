package kz.sofier.api.utils

import cats.effect.IO

package object config {
  case class ServerConfig(host: String, port: Int)
  case class DatabaseConfig(driver: String, url: String, user: String, password: String, schema: String)
  case class Config(server: ServerConfig, database: DatabaseConfig)

  object Config {
    import pureconfig._
    import pureconfig.module.catseffect._
    import pureconfig.generic.auto._

    def parse(): IO[Config] =
      ConfigSource.default.loadF[IO, Config]
  }
}
