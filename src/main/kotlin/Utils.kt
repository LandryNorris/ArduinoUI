import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun launch(dispatcher: CoroutineDispatcher = Dispatchers.Default, block: suspend () -> Unit) {
    CoroutineScope(dispatcher).launch {
        block()
    }
}
