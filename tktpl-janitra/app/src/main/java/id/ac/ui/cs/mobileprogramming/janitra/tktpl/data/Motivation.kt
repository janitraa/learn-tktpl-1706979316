package id.ac.ui.cs.mobileprogramming.janitra.tktpl.data

data class Motivation(val motivationText: String,
                 val name: String) {

    override fun toString(): String {
        return "$name - $motivationText"
    }
}