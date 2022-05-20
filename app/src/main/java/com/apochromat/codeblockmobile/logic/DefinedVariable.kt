package com.apochromat.codeblockmobile.logic

/**
 *  Блок определенной переменной.
 *  Позволяет объявить переменную и установить ей значение.
 **/
class DefinedVariable : Block() {
    private var value: Int = 0
    private var name: String = ""
    private var inputValue: String = ""
    private var inputName: String = ""

    init {
        setBlockType("DefinedVariable")
    }
    private fun initVar(){
        inputName = inputLeftEdit
        inputValue = inputRightEdit
    }

    fun setBlockInput(_name: String, _value: String) {
        inputName = _name
        inputValue = _value
    }

    override fun executeBlock() {
        super.executeBlock()
        initVar()
        if (heap.isArrayExist(inputName)) {
            setBlockStatus(typeMismatchArray(inputName))
            return
        }
        if (!variableCheck(inputName)) {
            setBlockStatus(incorrectNaming(inputName))
            return
        }
        val calculated = arithmetics(accessHeap(), inputValue)
        setBlockStatus(calculated.first)
        name = inputName
        if (calculated.first == ok()) {
            value = calculated.second
            accessHeap().setVariableValue(name, value)
        }
    }
}