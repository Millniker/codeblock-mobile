package com.apochromat.codeblockmobile

import com.apochromat.codeblockmobile.logic.*

fun main() {
    var ep = EntryPoint()
    var block_dv = DefinedVariable()
    var block_uv = UndefinedVariable()
    var blockIf = ConditionIf()
    var blockIfElse = ConditionIfElse()
    var block_aAlice = Assignment()
    var block_aBob = Assignment()
    var block_aTom = Assignment()
    var block_aSam = Assignment()
    var block_aKate = Assignment()

    block_dv.setBlockInput("tempVar", "0-10")
    block_uv.setBlockInput(listOf("Tom", "Sam", "Kate", "Bob", "Alice"))
    blockIf.setBlockInput("16", "16")
    blockIfElse.setBlockInput("16*2/2", "16", "==")
    block_aAlice.setBlockInput("Alice", "1111")
    block_aBob.setBlockInput("Bob", "1212")
    block_aTom.setBlockInput("Tom", "2*(1+ Alice)")
    block_aSam.setBlockInput("Sam", "1414")
    block_aKate.setBlockInput("Kate", "1515")

    connectBlocks(ep, block_dv)
    connectBlocks(block_dv, block_uv)
    connectBlocks(block_uv, blockIf)
    connectBlocks(blockIf.ifBegin, block_aAlice)
    connectBlocks(block_aAlice, blockIf.ifEnd)
    connectBlocks(blockIf, block_aBob)
    connectBlocks(block_aBob, blockIfElse)
    connectBlocks(blockIfElse.ifBegin, block_aTom)
    connectBlocks(block_aTom, blockIfElse.ifEnd)
    connectBlocks(blockIfElse.elseBegin, block_aSam)
    connectBlocks(block_aSam, blockIfElse.elseEnd)
    connectBlocks(blockIfElse, block_aKate)

    ep.run()

    println(Block().accessHeap().getVariablesList())
    println(Block().accessHeap().getVariableValue("tempVar"))
    println(Block().accessHeap().getVariableValue("Bob"))
    println(Block().accessHeap().getVariableValue("Alice"))
    println(Block().accessHeap().getVariableValue("Tom"))
    println(Block().accessHeap().getVariableValue("Sam"))
    println(Block().accessHeap().getVariableValue("Kate"))

}