class Solution {
    public int countPrimes(int n) {   
        if(n<2){
            return 0;
        }
        boolean[] isPrime = new boolean[n];

        // initially mrk all numbers as prime
        for(int i = 2 ; i<n; i++){
            isPrime[i] = true;
        }

        // sieve of eratosthenes
        for(int i = 2; i*i<n; i++){
            //remove the multiples of i
            if(isPrime[i]){
                for(int j = i*i; j<n; j=j+i){
                    isPrime[j] = false;
                }
            }
        }

        // count primes
        int count = 0;
        for(int i = 2; i<n; i++){
            if(isPrime[i]){
                count++;
            }
        }
        return count;
    }
}