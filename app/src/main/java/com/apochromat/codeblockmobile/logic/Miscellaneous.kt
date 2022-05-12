package com.apochromat.codeblockmobile.logic

fun expressionComparator(numberLeft: Int, numberRight: Int, comparator: String): Boolean {
    when (comparator) {
        ">" -> return (numberLeft > numberRight)
        ">=" -> return (numberLeft >= numberRight)
        "<" -> return (numberLeft < numberRight)
        "<=" -> return (numberLeft <= numberRight)
        "==" -> return (numberLeft == numberRight)
        "!=" -> return (numberLeft != numberRight)
    }
    return false
}

fun variableCheck(variable: String): Boolean {
    val str = variable.replace("[A-Za-z0-9_]".toRegex(), "")
    if (str.isNotEmpty()) {
        return false
    }
    return true
}

fun stringToList(string: String): List<String> {
    return string.replace("[\\s*]".toRegex(), "")
        .replace("[,;]".toRegex(), " ")
        .split("[\\s*]".toRegex()).filter { it.isNotEmpty() }
}