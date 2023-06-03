public class Main {
     public class HashMap{
            private class Entity{
                int Key;
                int Value;
            }
            private class Basket{
                Node Head;
                private class Node{
                    Entity entity;
                    Node next;
                }

                Entity find(int Key){
                    Node current = Head;
                    if(Head != null){
                        while (current != null){
                            if(current.entity.Key == Key){
                                return current.entity;
                            }
                            current = current.next;
                        }
                    }
                    return null;
                }

                private boolean push(Entity entity){
                    Node node = new Node();
                    node.entity = entity;

                    Node current = Head;
                    while(current != null){
                        if(current.entity == entity){
                            return false;
                        }
                        if(current.next == null){
                            current.next = node;
                            return true;
                        }
                        current = current.next;
                    }
                    Head = node;
                    return true;
                }

                private boolean del(int Key){
                    Node current = Head;
                    while(current != null){
                        if(current.next.entity.Key == Key){
                            if(current.next != null) {
                                current.next = current.next.next;
                            }else{
                                current.next = null;
                            }
                            return true;
                        }
                        current = current.next;
                    }
                    return false;
                }
            }
            private static final int INIT_SIZE = 16;
            private static final int Size = 0;
            private static final double LOAD_FACTOR = 0.75;

            private Basket baskets[];

            public HashMap() {
                this(INIT_SIZE);
            }

            public HashMap(int size) {
                baskets = new Basket[size];
            }

            int calcIndex(int Key){
                return Key % baskets.length;
            }

            public Entity find(int Key){
                int index = calcIndex(Key);
                Basket basket = baskets[index];

                if(basket != null){
                    return basket.find(Key);
                }
                return null;
            }

            public void push(int Key, int Value){
                int index = calcIndex(Key);
                Basket basket = baskets[index];

                Entity entity = new Entity();
                entity.Value = Value;
                entity.Key = Key;

                if(basket == null){
                    basket = new Basket();
                    baskets[index] = basket;
                }

                basket.push(entity);

            }

            public void del(int Key){
                int index = calcIndex(Key);
                Basket basket = baskets[index];

                if(basket != null){
                    basket.del(Key);
                }
            }
        }

    public class RedBlackTree{
        Node Root;
        private class Node{
            int Value;
            Node Left;
            Node Right;
            boolean isRed;

            Node(int value){
                Value = value;
                isRed = true;
            }
        }

        public void push(int Value){
            Root = push(Root, Value);
            Root.isRed = false;
        }

        private Node push(Node node, int Value){
            if(node == null){
                return new Node(Value);
            }

            if(Value < node.Value){
                node.Left = push(node.Left, Value);
            }else if(Value > node.Value){
                node.Right = push(node.Right, Value);
            }

            if(isRed(node.Right) && !isRed(node.Left)){
                node = rotateLeft(node);
            }

            if(isRed(node.Left) && isRed(node.Left.Left)){
                node = rotateRight(node);
            }

            if(isRed(node.Left) && isRed(node.Right)){
                flipColors(node);
            }

            return node;
        }

        private boolean isRed(Node node){
            if(node == null){
                return false;
            }
            return node.isRed;
        }

        private Node rotateLeft(Node node){
            Node x = node.Right;
            node.Right = x.Left;
            x.Left = node;
            x.isRed = node.isRed;
            node.isRed = true;
            return x;
        }

        private Node rotateRight(Node node){
            Node x = node.Left;
            node.Left = x.Right;
            x.Right = node;
            x.isRed = node.isRed;
            node.isRed = true;
            return x;
        }

        private void flipColors(Node node){
            node.isRed = true;
            node.Left.isRed = false;
            node.Right.isRed = false;
        }



        public static void main(String[] args) {

        }
    }
}
