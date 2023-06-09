package com.example.popcorn.extensions

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import com.devs.readmoreoption.ReadMoreOption
import com.example.popcorn.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.ref.WeakReference

fun TextView.setReadMoreText(text: String?, collapsedLinesCount: Int = 3) {
    val readMoreOption = ReadMoreOption.Builder(this.context)
        .textLength(collapsedLinesCount, ReadMoreOption.TYPE_LINE)
        .moreLabel("Read more")
        .lessLabel("See less")
        .moreLabelColor(ContextCompat.getColor(this.context, R.color.text_secondary))
        .lessLabelColor(ContextCompat.getColor(this.context, R.color.text_secondary))
        .build()

    readMoreOption.addReadMoreTo(this, text)
}

fun TextView.getTextLineCount(text: String, lineCount: (Int) -> (Unit)) {
    val params: PrecomputedTextCompat.Params = TextViewCompat.getTextMetricsParams(this)
    val ref: WeakReference<TextView>? = WeakReference(this)

    GlobalScope.launch(Dispatchers.Default) {
        val text = PrecomputedTextCompat.create(text, params)
        GlobalScope.launch(Dispatchers.Main) {
            ref?.get()?.let { textView ->
                TextViewCompat.setPrecomputedText(textView, text)
                lineCount.invoke(textView.lineCount)
            }
        }
    }
}