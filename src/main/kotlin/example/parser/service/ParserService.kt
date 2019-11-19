package example.parser.service

import arrow.core.Option
import arrow.extension
import arrow.fx.reactor.k
import arrow.typeclasses.Show
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.*

interface ParserService {
    suspend fun parse(keyType: String): ParseResult
}

data class ParseResult(val type: String) {
    companion object
}

@extension
interface ResultShow : Show<ParseResult> {
    override fun ParseResult.show(): String {
        return "ParseResult: {type - $type}"
    }
}

data class ParseConfig(val resolveLocation: Boolean, val locale: Option<Locale>)


@Component
class ReactiveParserService : ParserService {

    override suspend fun parse(key: String): ParseResult {
        var sample = aux()
        var x = certainClient().k().suspended()
        return ParseResult(key + sample)
    }

    suspend fun aux(): Int = 3

    fun certainClient(): Mono<Int> = Mono.just(1)

}

