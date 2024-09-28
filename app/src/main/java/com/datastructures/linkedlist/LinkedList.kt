package com.datastructures.linkedlist

class LinkedList<T : Any> {

    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    private var size = 0

    private val isEmpty: Boolean
        get() = size == 0

    override fun toString(): String {
        return if (isEmpty) {
            "Empty list"
        } else {
            head.toString()
        }
    }

    fun push(value: T): LinkedList<T> = apply {
        head = Node(value, head)
        if (tail == null) {
            tail = head
        }
        size++
    }

    fun pop(): T? {
        if (isEmpty) return null

        val result = head?.value
        head = head?.next
        size--

        if (isEmpty) {
            tail = null
        }

        return result
    }

    fun append(value: T): LinkedList<T> = apply {
        if (isEmpty) {
            push(value)
            return this
        }

        val node = Node(value)
        tail!!.next = node
        tail = node
        size++
    }

    fun nodeAt(index: Int): Node<T>? {
        var currentNode = head
        var currentIndex = 0

        while (currentNode != null && currentIndex < index) {
            currentNode = currentNode.next
            currentIndex++
        }

        return currentNode
    }

    fun insert(value: T, afterNode: Node<T>): Node<T> {
        if (tail == afterNode) {
            append(value)
            return tail!!
        }

        val newNode = Node(value, afterNode.next)
        afterNode.next = newNode
        size++
        return newNode
    }

    fun removeLast(): T? {
        val head = head ?: return null

        if (head.next == null) return pop()

        var prev = head
        var current = head
        var next = current.next

        while (next != null) {
            prev = current
            current = next
            next = current.next
        }

        prev.next = null
        tail = prev
        size--

        return current.value
    }

    fun removeAfter(node: Node<T>): T? {
        val result = node.next?.value

        if (node.next == tail) {
            tail = node
        }

        if (node.next != null) {
            size--
        }

        node.next = node.next?.next

        return result
    }
}
