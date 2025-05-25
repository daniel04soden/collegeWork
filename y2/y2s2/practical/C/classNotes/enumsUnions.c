enum {  // Mainly used for storing integer variables 
  // Can be generic with no vall asigned or can assign a value
  VALONE =5,
  VALTWO,
  VALTHREE,
  VALFOUR = 20,
  VALFIVE = 39
};
typedef unsigned char uint8;
typedef unsigned short uint16;
typedef struct tcp{
  int version;
  int flag;
  int source;
  int dst;
}tcp;
typedef struct udp{
  int checksum;
  int src,dst;
}udp;

enum {  
TCP_PLD,
UDP_PLD
};

union payload {
  tcp tcp;
  struct udp udp;
};

int main(int argc, char *argv[])
{
  int myvar = VALONE;
  switch (myvar) {
    case VALONE: 
    break;

    case VALTWO: 
    break;

  }

  int version;
  int src,dst;

  union payload pld;
  return 0;
}
