package com.ggdsn.algorithms.graph

import com.ggdsn.algorithms.alg4work.Alg4
import java.io.File
import java.io.FileInputStream
import java.util.*
import kotlin.Comparator

interface MinSpanningTree {
    fun getEdges(): Iterable<WeightEdge>
    fun allWeight(): Double
}

class MinSpanningTreeImpl(private val graph: WeightGraph) : MinSpanningTree {
    private val marked = Array(graph.edgeCount(), { false })
    private val tree = mutableListOf<WeightEdge>()
    private val queue: PriorityQueue<WeightEdge> = PriorityQueue(Comparator<WeightEdge> { o1, o2 ->
        return@Comparator Math.round(o1.weight * 100000 - o2.weight * 100000).toInt()
    })

    init {
        visit(0)
        while (queue.isNotEmpty()) {
            val edge = queue.poll()
            val v = edge.p1
            val w = edge.p2
            if (marked[v] && marked[w]) continue
            tree.add(edge)
            if (marked[w]) visit(v)
            else visit(w)
        }
    }

    private fun visit(vertex: Int) {
        val adj = graph.getAdjacent(vertex)
        marked[vertex] = true
        for (e in adj) {
            if (!marked[e.other(vertex)])
                queue.add(e)
        }
    }

    override fun getEdges(): Iterable<WeightEdge> = tree

    override fun allWeight(): Double = tree.sumByDouble { it.weight }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val scanner = Scanner(FileInputStream(Alg4.DATA_DIR + File.separator + args[0]))
            val graph = WeightGraphImpl(scanner.nextInt())
            val edgeCount = scanner.nextInt()
            for (i in 0 until edgeCount) {
                graph.addEdge(WeightEdge(scanner.nextInt(), scanner.nextInt(), scanner.nextDouble()))
            }

            val mst = MinSpanningTreeImpl(graph)
            for (t in mst.getEdges()) {
                println(t)
            }
            println("weight sum =${mst.allWeight()}")
        }
    }
}