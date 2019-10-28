package kz.sofier.api.utils

import cats.effect.IO

package object config {
  case class ServerConfig(host: String, port: Int)
  case class Config(server: ServerConfig)

  object Config {
    import pureconfig._
    import pureconfig.module.catseffect._
    import pureconfig.generic.auto._

    def parse(): IO[Config] =
      ConfigSource.default.loadF[IO, Config]
  }
}
