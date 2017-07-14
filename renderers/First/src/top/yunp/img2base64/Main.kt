package top.yunp.img2base64

import org.w3c.dom.Element
import org.w3c.dom.HTMLFormElement
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.dom.events.Event
import org.w3c.dom.get
import org.w3c.files.File
import org.w3c.files.FileReader
import kotlin.browser.document

/**
 * Created by plter on 7/14/17.
 */
class Main {

    val layout: Element = document.createElement("div")
    var fileForm: HTMLFormElement? = null
    var outputText: HTMLTextAreaElement? = null

    init {
        buildUI()
        addListeners()
    }

    private fun addListeners() {
        fileForm?.onsubmit = fun(e: Event) {
            e.preventDefault()

            val fileInput: dynamic = fileForm!!.get("file")
            val files: Array<File>? = fileInput.files
            val first: File? = files?.get(0)
            if (first != null) {
                val reader = FileReader()
                reader.onload = fun(_: Event) {
                    outputText!!.value = reader.result
                }
                reader.readAsDataURL(first)
            }
        }
    }

    private fun buildUI(): Unit {

        document.title = "文件转base64工具"

        document.body?.appendChild(layout)
        layout.innerHTML = "<div>" +
                "<form class='file-form'>" +
                "   <input type='file' name='file'>" +
                "   <input type='submit' value='转换'>" +
                "</form>" +
                "</div>" +
                "<div>" +
                "   <textarea class='output' style='width:500px;height:400px;'></textarea>" +
                "</div>"
        fileForm = document.querySelector(".file-form") as HTMLFormElement
        outputText = document.querySelector(".output") as HTMLTextAreaElement
    }
}