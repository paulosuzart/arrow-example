package example.parser.controller

import arrow.fx.reactor.FluxK
import arrow.fx.reactor.ForMonoK
import arrow.fx.reactor.extensions.fluxk.async.effect
import arrow.fx.reactor.extensions.fluxk.monad.flatMap
import arrow.fx.reactor.extensions.fluxk.monad.flatTap
import arrow.fx.reactor.extensions.fluxk.traverse.flatTraverse
import arrow.fx.reactor.value
import example.parser.service.ParserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux


@RestController
class ParserController(
        private val parserService: ParserService
) {

    @GetMapping("/parse")
    fun parse3(@RequestParam("keys") keys: Set<String>) = FluxK(Flux.fromIterable(keys)).flatMap { i ->
        effect { parserService.parse(i) }
    }.value()

}

