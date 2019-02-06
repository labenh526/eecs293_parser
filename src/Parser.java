import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Parser {


//    public static void main(String args[]) {
//        LeafNode divide = LeafNode.build(Connector.build(TerminalSymbol.DIVIDE));
//        LeafNode c = LeafNode.build(Variable.build("c"));
//        LeafNode b = LeafNode.build(Variable.build("b"));
//        LeafNode plus = LeafNode.build(Connector.build(TerminalSymbol.PLUS));
//        LeafNode a = LeafNode.build(Variable.build("a"));
//
//        ArrayList<Node> node1children = new ArrayList<>();
//        node1children.add(divide);
//        node1children.add(c);
//        InternalNode one = InternalNode.build(node1children);
//
//        ArrayList<Node> node2children = new ArrayList<>();
//        node2children.add(b);
//        node2children.add(one);
//        InternalNode two = InternalNode.build(node2children);
//
//        ArrayList<Node> node3children = new ArrayList<>();
//        node3children.add(plus);
//        node3children.add(two);
//        InternalNode three = InternalNode.build(node3children);
//
//        ArrayList<Node> node4children = new ArrayList<>();
//        node4children.add(a);
//        node4children.add(three);
//        InternalNode four = InternalNode.build(node4children);
//
//        System.out.println(four.toList());
//        System.out.println(four.toString());
//    }
//}
