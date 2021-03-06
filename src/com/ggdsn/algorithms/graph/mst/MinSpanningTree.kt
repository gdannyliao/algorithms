package com.ggdsn.algorithms.graph.mst

import com.ggdsn.algorithms.alg4work.Alg4
import com.ggdsn.algorithms.graph.WeightEdge
import com.ggdsn.algorithms.graph.WeightGraphImpl
import java.io.File
import java.io.FileInputStream
import java.util.*

interface MinSpanningTree {
    fun getEdges(): Iterable<WeightEdge>
    fun allWeight(): Double
}

class Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val scanner = Scanner(FileInputStream(Alg4.DATA_DIR + File.separator + args[0]))
            val graph = WeightGraphImpl(scanner.nextInt())
            val edgeCount = scanner.nextInt()
            for (i in 0 until edgeCount) {
                graph.addEdge(WeightEdge(scanner.nextInt(), scanner.nextInt(), scanner.nextDouble()))
            }

            val mst = MinSpanningTreeKuskal(graph)
            for (t in mst.getEdges()) {
                println(t)
            }
            println("weight sum =${mst.allWeight()}")
        }
    }
}