"""
MultiSet ADT

Note: you can open this project in PyCharm or your favourite IDE if you want
to try running it.

The output should look something like the below (timing will vary of course):

  500        <class '__main__.BSTMultiSet'>  0.000632
 1000        <class '__main__.BSTMultiSet'>  0.001360
 2000        <class '__main__.BSTMultiSet'>  0.002675
 4000        <class '__main__.BSTMultiSet'>  0.004956
  500 <class '__main__.LinkedListMultiSet'>  0.001782
 1000 <class '__main__.LinkedListMultiSet'>  0.004830
 2000 <class '__main__.LinkedListMultiSet'>  0.013345
 4000 <class '__main__.LinkedListMultiSet'>  0.041657
  500  <class '__main__.ArrayListMultiSet'>  0.000072
 1000  <class '__main__.ArrayListMultiSet'>  0.000170
 2000  <class '__main__.ArrayListMultiSet'>  0.000491
 4000  <class '__main__.ArrayListMultiSet'>  0.001642
  500       <class '__main__.TreeMultiSet'>  0.004821
 1000       <class '__main__.TreeMultiSet'>  0.014988
 2000       <class '__main__.TreeMultiSet'>  0.041059
 4000       <class '__main__.TreeMultiSet'>  0.132969

You might also find it helpful to 'Split Right' or 'Split Down' in the editor by
right-clicking the tab above with this file's name on it, to allow you to look at
the python code as you write your corresponding Java code.

"""
from __future__ import annotations

import random
import time


class MultiSet:
    """
    An abstract class representing the MultiSet ADT, which supports the
    add, remove, is_empty, count, and contains operations.

    This class itself does not handle how the underlying data is stored,
    so it just inherits Object.__init__.
    """

    def add(self, item: int) -> None:
        raise NotImplementedError

    def remove(self, item: int) -> None:
        raise NotImplementedError

    def contains(self, item: int) -> bool:
        raise NotImplementedError

    def is_empty(self) -> bool:
        raise NotImplementedError

    def count(self, item: int) -> int:
        raise NotImplementedError

    def size(self) -> int:
        raise NotImplementedError


class BST:
    """Binary Search Tree class.

    This class represents a binary tree satisfying the Binary Search Tree
    property: for every item, its value is >= all items stored in its left
    subtree, and <= all items stored in its right subtree.

    Note: items that are equal to the root may appear in either subtree.
    """
    # === Private Attributes ===
    # The item stored at the root of the tree, or None if the tree is empty.
    _root: int | None
    # The left subtree, or None if the tree is empty.
    _left: BST | None
    # The right subtree, or None if the tree is empty.
    _right: BST | None

    # === Representation Invariants ===
    #  - If self._root is None, then so are self._left and self._right.
    #    This represents an empty BST.
    #  - If self._root is not None, then self._left and self._right
    #    are BinarySearchTrees.
    #  - (BST Property) If self is not empty, then
    #    all items in self._left are <= self._root, and
    #    all items in self._right are >= self._root.

    def __init__(self, root: int | None) -> None:
        """Initialize a new BST containing only the given root value.

        If <root> is None, initialize an empty tree.
        """
        if root is None:
            self._root = None
            self._left = None
            self._right = None
        else:
            self._root = root
            self._left = BST(None)
            self._right = BST(None)

    def is_empty(self) -> bool:
        """Return whether this BST is empty.
        """
        return self._root is None

    def __contains__(self, item: int) -> bool:
        """Return whether <item> is in this BST.
        """
        if self.is_empty():
            return False
        elif item == self._root:
            return True
        elif item < self._root:
            return item in self._left
        else:
            return item in self._right

    def insert(self, item: int) -> None:
        """Insert <item> into this tree.
        """
        if self.is_empty():
            # Make new leaf.
            # Note that self._left and self._right cannot be None when the
            # tree is non-empty! (This is one of our invariants.)
            self._root = item
            self._left = BST(None)
            self._right = BST(None)
        elif item <= self._root:
            self._left.insert(item)
        else:
            self._right.insert(item)

    def delete(self, item: int) -> None:
        """Remove *one* occurrence of <item> from this BST.

        Do nothing if <item> is not in the BST.
        """
        if self.is_empty():
            pass
        elif self._root == item:
            self.delete_root()
        elif item < self._root:
            self._left.delete(item)
        else:
            self._right.delete(item)

    def delete_root(self) -> None:
        """Remove the root of this tree.

        Precondition: this tree is *non-empty*.
        """
        if self._left.is_empty() and self._right.is_empty():
            self._root = None
            self._left = None
            self._right = None
        elif self._left.is_empty():
            # "Promote" the right subtree.
            # Note that self = self._right does NOT work!
            self._root, self._left, self._right = \
                self._right._root, self._right._left, self._right._right
        elif self._right.is_empty():
            # "Promote" the left subtree.
            self._root, self._left, self._right = \
                self._left._root, self._left._left, self._left._right
        else:
            # Both subtrees are non-empty. Can choose to replace the root
            # from either the max value of the left subtree, or the min value
            # of the right subtree.
            # (Implementations are very similar, but we'll take the max from the left here.)
            self._root = self._left._extract_max()

    def _extract_max(self) -> int:
        """Remove and return the maximum item stored in this tree.

        Precondition: this tree is *non-empty*.
        """
        if self._right.is_empty():
            max_item = self._root
            # "Promote" the left subtree.
            # Alternate approach: call self.delete_root()!
            self._root, self._left, self._right = \
                self._left._root, self._left._left, self._left._right
            return max_item
        else:
            return self._right._extract_max()

    def height(self) -> int:
        """Return the height of this BST.
        """
        if self.is_empty():
            return 0
        else:
            return max(self._left.height(), self._right.height()) + 1

    def count(self, item: int) -> int:
        """Return the number of occurrences of <item> in this BST.
        """
        if self.is_empty():
            return 0
        elif self._root > item:
            return self._left.count(item)
        elif self._root == item:
            return 1 + self._left.count(item) + self._right.count(item)
        else:
            return self._right.count(item)

    def __len__(self) -> int:
        """Return the number of items in this BST.
        """
        if self.is_empty():
            return 0
        return 1 + len(self._left) + len(self._right)


class BSTMultiSet(MultiSet):
    """
    This class uses an underlying BST to implement our MultiSet ADT.
    """

    _tree: BST

    def __init__(self):
        self._tree = BST(None)

    def add(self, item: int) -> None:
        self._tree.insert(item)

    def remove(self, item: int) -> None:
        self._tree.delete(item)

    def contains(self, item: int) -> bool:
        return item in self._tree

    def is_empty(self) -> bool:
        return self._tree.is_empty()

    def count(self, item: int) -> int:
        return self._tree.count(item)

    def size(self) -> int:
        return len(self._tree)


class Tree:
    """A recursive tree data structure, which provides services required of the
       MultiSet ADT. See TreeMultiSet, which is the next class defined.

       This is a simplified version of the Tree data structure
       adapted from CSC148.
    """
    # === Private Attributes ===
    # The item stored at this tree's root, or None if the tree is empty.
    _root: int | None
    # The list of all subtrees of this tree.
    _subtrees: list[Tree]

    # === Representation Invariants ===
    # - If self._root is None then self._subtrees is an empty list.
    #   This setting of attributes represents an empty tree.
    #
    #   Note: self._subtrees may be empty when self._root is not None.
    #   This setting of attributes represents a tree consisting of just one
    #   node.

    def __init__(self, root: int | None,
                 subtrees: None | list[Tree] = None) -> None:
        """Initialize a new Tree with the given root value and subtrees.

        If <root> is None, the tree is empty.
        Precondition: if <root> is None, then <subtrees> is empty.
        """
        self._root = root
        if subtrees is None:
            self._subtrees = []
        else:
            self._subtrees = subtrees[:]

    def is_empty(self) -> bool:
        """Return whether this tree is empty.

        >>> t1 = Tree(None, [])
        >>> t1.is_empty()
        True
        >>> t2 = Tree(3, [])
        >>> t2.is_empty()
        False
        """
        return self._root is None

    def __len__(self) -> int:
        """Return the number of items contained in this tree.

        >>> t1 = Tree(None, [])
        >>> len(t1)
        0
        >>> t2 = Tree(3, [Tree(4, []), Tree(1, [])])
        >>> len(t2)
        3
        """
        if self.is_empty():
            return 0
        else:
            size = 1  # count the root
            for subtree in self._subtrees:
                size += subtree.__len__()  # could also do len(subtree) here
            return size

    def count(self, item: int) -> int:
        """Return the number of occurrences of <item> in this tree.
        >>> t = Tree(3, [Tree(4, []), Tree(1, [])])
        >>> t.count(3)
        1
        >>> t.count(100)
        0
        """
        if self.is_empty():
            return 0
        else:
            num = 0
            if self._root == item:
                num += 1
            for subtree in self._subtrees:
                num += subtree.count(item)
            return num

    def __str__(self) -> str:
        """Return a string representation of this tree.

        For each node, its item is printed before int of its
        descendants' items. The output is nicely indented.

        You may find this method helpful for debugging.
        """
        # First version is commented out. This had the problem that it doesn't
        # distinguish between different levels in the tree, and just prints out
        # every item on a new line.
        # if self.is_empty():
        #     return ''
        # else:
        #     s = f'{self._root}\n'
        #     for subtree in self._subtrees:
        #         s += str(subtree)  # equivalent to subtree.__str__()
        #     return s
        #
        # Instead, we call a recursive helper method.
        return self._str_indented()

    def _str_indented(self, depth: int = 0) -> str:
        """Return an indented string representation of this tree.

        The indentation level is specified by the <depth> parameter.
        """
        if self.is_empty():
            return ''
        else:
            s = '  ' * depth + str(self._root) + '\n'
            for subtree in self._subtrees:
                # Note that the 'depth' argument to the recursive call is
                # modified.
                s += subtree._str_indented(depth + 1)
            return s

    def average(self) -> float:
        """Return the average of all the values in this tree.

        Return 0.0 if this tree is empty.

        Precondition: this is a tree of numbers.

        >>> Tree(None, []).average()
        0.0
        >>> t = Tree(13, [Tree(2, []), Tree(6, [])])
        >>> t.average()
        7.0
        >>> lt = Tree(2, [Tree(4, []), Tree(5, [])])
        >>> rt = Tree(3, [Tree(6, []), Tree(7, []), Tree(8, []), Tree(9, []),\
                          Tree(10, [])])
        >>> t = Tree(1, [lt, rt])
        >>> t.average()
        5.5
        """
        if self.is_empty():
            return 0.0
        else:
            total, count = self._average_helper()
            return total / count

    def _average_helper(self) -> tuple[int, int]:
        """Return a tuple (x,y) where:

        x is the total values in this tree, and
        y is the size of this tree.

        >>> lt = Tree(2, [Tree(4, []), Tree(5, [])])
        >>> rt = Tree(3, [Tree(6, []), Tree(7, []), Tree(8, []), Tree(9, []),\
                          Tree(10, [])])
        >>> t = Tree(1, [lt, rt])
        >>> t._average_helper()
        (55, 10)
        """
        if self.is_empty():
            return 0, 0
        else:
            total = self._root
            size = 1
            for subtree in self._subtrees:
                subtree_total, subtree_size = subtree._average_helper()
                total += subtree_total
                size += subtree_size
            return total, size

    def __eq__(self, other: Tree) -> bool:
        """Return whether <self> and <other> are equal.
        """
        if self.is_empty() and other.is_empty():
            return True
        elif self.is_empty() or other.is_empty():
            return False
        else:
            if self._root != other._root:
                return False

            if len(self._subtrees) != len(other._subtrees):
                return False

            return self._subtrees == other._subtrees

    def __contains__(self, item: int) -> bool:
        """Return whether <item> is in this tree.

        >>> t = Tree(1, [Tree(2, []), Tree(5, [])])
        >>> 1 in t  # Same as t.__contains__(1)
        True
        >>> 5 in t
        True
        >>> 4 in t
        False
        """
        if self.is_empty():
            return False

        # item may in root, or subtrees
        if self._root == item:
            return True
        else:
            for subtree in self._subtrees:
                if item in subtree:
                    return True
            return False

    def leaves(self) -> list[int]:
        """Return a list of all the leaf items in the tree.

        >>> Tree(None, []).leaves()
        []
        >>> t = Tree(1, [Tree(2, []), Tree(5, [])])
        >>> t.leaves()
        [2, 5]
        >>> lt = Tree(2, [Tree(4, []), Tree(5, [])])
        >>> rt = Tree(3, [Tree(6, []), Tree(7, [])])
        >>> t = Tree(1, [lt, rt])
        >>> t.leaves()
        [4, 5, 6, 7]
        """
        if self.is_empty():
            return []
        elif not self._subtrees:
            # The elif condition is equivalent to this: self._subtrees == []
            return [self._root]
        else:
            leaves = []
            for subtree in self._subtrees:
                leaves.extend(subtree.leaves())
            return leaves

    # -------------------------------------------------------------------------
    # Mutating methods
    # -------------------------------------------------------------------------
    def delete_item(self, item: int) -> bool:
        """Delete *one* occurrence of the given item from this tree.

        Return True if <item> was deleted, and False otherwise.
        Do not modify this tree if it does not contain <item>.

        >>> Tree(None, []).delete_item(99)
        False
        >>> lt = Tree(2, [Tree(4, []), Tree(5, [])])
        >>> rt = Tree(3, [Tree(6, []), \
                          Tree(7, []), \
                          Tree(8, []), \
                          Tree(9, []), \
                          Tree(10, [Tree(11, [Tree(13, []), Tree(14, [])]), \
                                    Tree(12, [])])])
        >>> t = Tree(1, [lt, rt])
        >>> t.delete_item(99)
        False
        >>> t.delete_item(3)
        True
        >>> 3 in t
        False
        """
        if self.is_empty():
            # The item is not in the tree.
            return False
        elif self._root == item:
            # We've found the item: now delete it.
            self._delete_root()
            return True
        else:
            # Loop through each subtree, and stop the first time
            # the item is deleted. (This is why a boolean is returned!)
            for subtree in self._subtrees:
                deleted = subtree.delete_item(item)
                if deleted and subtree.is_empty():
                    # The item was deleted and the subtree is now empty.
                    # We should remove the subtree from the list of subtrees.
                    # Note that mutate a list while looping through it is
                    # EXTREMELY DANGEROUS!
                    # We are only doing it because we return immediately
                    # afterwards, and so no more loop iterations occur.
                    self._subtrees.remove(subtree)
                    return True
                elif deleted:
                    # The item was deleted, and the subtree is not empty.
                    return True
                else:
                    # No item was deleted. Continue onto the next subtree.
                    # Note that this branch is unnecessary; we've only shown
                    # it to write comments.
                    pass

            # If we don't return inside the loop, the item is not deleted
            # from int of the subtrees. In this case, the item does not
            # appear in this tree.
            return False

    def _delete_root(self) -> None:
        """Remove the root item of this tree.

        Precondition: this tree has at least one subtree.
        """
        if not self._subtrees:
            # This is a leaf. Deleting the root gives and empty tree.
            self._root = None
        else:
            # Get the last subtree in this tree.
            chosen_subtree = self._subtrees.pop()

            self._root = chosen_subtree._root
            self._subtrees.extend(chosen_subtree._subtrees)

            # Strategy 2: Replace with a leaf.
            # 1. Extract the leftmost leaf (using another helper).
            # leaf = self._extract_leaf()
            #
            # 2. Update self._root. (Note that self._subtrees remains the same.)
            # self._root = leaf

    def _extract_leaf(self) -> int:
        """Remove and return the leftmost leaf in a tree.

        Precondition: this tree is non-empty.
        """
        if not self._subtrees:
            old_root = self._root
            self._root = None
            return old_root
        else:
            leaf = self._subtrees[0]._extract_leaf()
            # Need to check whether self._subtrees[0] is now empty,
            # and if so, remove it.
            if self._subtrees[0].is_empty():
                self._subtrees.pop(0)

            return leaf

    def insert(self, item: int) -> None:
        """Insert <item> into this tree using the following algorithm.

            1. If the tree is empty, <item> is the new root of the tree.
            2. If the tree has a root but no subtrees, create a
               new tree containing the item, and make this new tree a subtree
               of the original tree.
            3. Otherwise, pick a random number between 1 and 3 inclusive.
                - If the random number is 3, create a new tree containing
                  the item, and make this new tree a subtree of the original.
                - If the random number is a 1 or 2, pick one of the existing
                  subtrees at random, and *recursively insert* the new item
                  into that subtree.

        >>> t = Tree(None, [])
        >>> t.insert(1)
        >>> 1 in t
        True
        >>> lt = Tree(2, [Tree(4, []), Tree(5, [])])
        >>> rt = Tree(3, [Tree(6, []), Tree(7, []), Tree(8, []), Tree(9, []),\
                          Tree(10, [])])
        >>> t = Tree(1, [lt, rt])
        >>> t.insert(100)
        >>> 100 in t
        True
        """
        if self.is_empty():
            self._root = item
        elif not self._subtrees:
            self._subtrees = [Tree(item, [])]
        else:
            if random.randint(1, 3) == 3:
                self._subtrees.append(Tree(item, []))
            else:
                subtree_index = random.randint(0, len(self._subtrees) - 1)
                self._subtrees[subtree_index].insert(item)

    def insert_child(self, item: int, parent: int) -> bool:
        """Insert <item> into this tree as a child of <parent>.

        If successful, return True. If <parent> is not in this tree,
        return False.

        If <parent> appears more than once in this tree, <item> should only
        be inserted once (you can pick where to insert it).
        """
        if self.is_empty():
            return False
        elif self._root == parent:
            self._subtrees.append(Tree(item, []))
            return True
        else:
            for subtree in self._subtrees:
                if subtree.insert_child(item, parent):
                    return True
            return False


class TreeMultiSet(MultiSet):
    """
    This class uses an underlying Tree to implement our MultiSet ADT.
    """

    _tree: Tree

    def __init__(self):
        self._tree = Tree(None)

    def add(self, item: int) -> None:
        self._tree.insert(item)

    def remove(self, item: int) -> None:
        self._tree.delete_item(item)

    def contains(self, item: int) -> bool:
        return item in self._tree

    def is_empty(self) -> bool:
        return self._tree.is_empty()

    def count(self, item: int) -> int:
        return self._tree.count(item)

    def size(self) -> int:
        return len(self._tree)


class ArrayListMultiSet(MultiSet):
    """
    This class uses an underlying list to implement our MultiSet ADT.
    """

    _list: list

    def __init__(self):
        self._list = []

    def add(self, item: int) -> None:
        self._list.append(item)

    def remove(self, item: int) -> None:
        # we check that the item exists to avoid raising a ValueError,
        # since the MultiSet ADT doesn't say it will raise such an error!
        if item in self._list:
            self._list.remove(item)

    def contains(self, item: int) -> bool:
        return item in self._list

    def is_empty(self) -> bool:
        return len(self._list) == 0

    def count(self, item: int) -> int:
        return self._list.count(item)

    def size(self) -> int:
        return len(self._list)


class LinkedListMultiSet(MultiSet):
    """
    Unlike the others, this implementation does not just "wrap" an
    underlying data structure, it is instead a custom LinkedList implementation, which
    directly provides the necessary MultiSet methods.

    Representation Invariant:
    self._front is None represents an empty MultiSet
    """

    _front: _Node | None
    _size: int

    def __init__(self):
        self._front = None
        self._size = 0

    def add(self, item: int) -> None:
        new_node = _Node(item)
        new_node.next = self._front
        self._front = new_node
        self._size += 1

    def remove(self, item: int) -> None:
        cur = self._front
        prev = None
        while cur is not None:
            if cur.item == item:
                self._size -= 1
                if prev:
                    prev.next = cur.next
                else:  # first item
                    self._front = cur.next
                return
            prev, cur = cur, cur.next
        # if here, item not found
        return

    def contains(self, item: int) -> bool:
        cur = self._front
        while cur is not None:
            if cur.item == item:
                return True
            cur = cur.next
        return False

    def is_empty(self) -> bool:
        return self._front is None

    def count(self, item: int) -> int:
        num_seen = 0
        cur = self._front
        while cur is not None:
            if cur.item == item:
                num_seen += 1
            cur = cur.next
        return num_seen

    def size(self) -> int:
        return self._size


class _Node:
    """
    Internal node structure used by the LinkedListMultiSet above.
    """
    item: int
    next: _Node | None

    def __init__(self, item: int) -> None:
        self.item = item
        self.next = None


def profileMultiSet(my_input: MultiSet, n: int) -> None:
    """
    Run the timing experiment for the given <my_input> MultiSet implementation,
    for a problem size of <n>.
    """
    # add n random items, then remove them all; we will only time the removal
    # step.
    items_added = []
    for i in range(n):
        x = random.randint(0, 100)
        my_input.add(x)
        items_added.append(x)

    # sanity check that we added n items
    assert my_input.size() == n

    start = time.time()

    for x in items_added:
        my_input.remove(x)

    end = time.time()

    # sanity check that we successfully removed all the items we had added!
    assert my_input.is_empty()

    # just print a quick summary of what we just ran
    print(f'{n}'.rjust(5), f'{my_input.__class__}'.rjust(37),
          f'{end - start : .6f}')


# For the main block, some parts won't neatly translate to Java,
if __name__ == '__main__':
    # import doctest
    # doctest.testmod()

    # we won't bother running pyTA for this :)
    # import python_ta
    # python_ta.check_all(config={'extra-imports': ['random']})

    # perform a little timing experiment
    multisets = [BSTMultiSet(), LinkedListMultiSet(), ArrayListMultiSet(), TreeMultiSet()]
    for multiset in multisets:
        for n in [500, 1000, 2000, 4000]:
            profileMultiSet(multiset, n)
