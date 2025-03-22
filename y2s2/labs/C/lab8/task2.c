#include <stdio.h>
#include <stdlib.h>
struct node{
    int val;
    struct node *children;
    int num_children;
};

typedef struct list_node{
  int data;
  struct list_node *next;
}list_node;

/**
 * Depth-first search
 * ==================
 * We have to process the first child of each node until we hit NULL.
 * At that point we have to go back to the parent and continue to the
 * next child.
 * This indicates the need for a stack data structure because we work
 * with the top of the stack, we push items on the stack (exploring a
 * child node) and pop items from the stack (going back to the parent).
 * For each node on the stack we need to keep track of which child should
 * be explored next.
 * Most common implementation is to push the children onto the stack
 * in reverse order. This means that the first child will be top of the
 * stack, followed by next child, etc. When we are done exploring the
 * first child we pop it, and the next node is the second child.
 * It's important to overwrite the parent node with the first child,
 * because we don't want to revisit the parent.
 */

struct node *dfs_scan(struct node *root){
    struct node *stack[100]; // this is where we store the nodes
    int stack_top_idx = 0;

    // Start by putting the root onto the stack
    stack[stack_top_idx] = root;

    // Stop the search when we are back at the bottom of the stack
    while (stack_top_idx >= 0){
        printf("%d\n", stack[stack_top_idx]->val);

        // Not here, look into the children.
        // If the node doesn't have children we go back to the 
        // previous node on the stack
        if (stack[stack_top_idx]->num_children == 0){
            stack_top_idx--;
            printf("\n");
            continue; // Skip to the start of the loop
        }

        // Add any children in reverse order
        // We need to save the current node
        struct node *crt = stack[stack_top_idx];
        // Decrement the stack pointer first so that first
        // child // added will replace the parent on the stack.
        // We are not interested in the parent anymore 
        // since we've already explored it.
        stack_top_idx--;
        for (int i = crt->num_children-1;i>=0;i--){
            // Increment the stack index
            stack_top_idx++;
            // Add the next child
            stack[stack_top_idx] = &crt->children[i];
        }
    }

    return NULL;
}


list_node *bfs_scan(list_node *root){
    
}

/**
 * Create a tree
 */
struct node *create_tree(){
    struct node *n;
    srandom(1); // Initialise PRNG
    n = malloc(sizeof(struct node));
    n->val = random()%100;
    printf("%d\n", n->val);
    n->num_children = 3;
    // Level 1
    n->children = malloc(3*sizeof(struct node));
    for (int i=0;i<3;i++){
        struct node *l1 = &n->children[i];
        l1->val = random()%100;
        printf("L1: %d\n", l1->val);
        l1->num_children = 2;
        // Level 2
        l1->children = malloc(2*sizeof(struct node));
        for (int j=0;j<2;j++){
            struct node *l2 = &l1->children[j];
            l2->val = random()%100;
            printf("L2: %d\n", l2->val);
            l2->num_children = 1;
            // Level 3
            l2->children = malloc(sizeof(struct node));
            for (int k=0;k<1;k++){
                struct node *l3 = &l2->children[k];
                l3->val = random()%100;
                printf("L3: %d\n", l3->val);
                l3->num_children = 0;
                // These are leaves
                l3->children = NULL;
            }
        }
    }

    return n;
}

void main(){
    struct node *root = create_tree();
    dfs_scan(root);
}
