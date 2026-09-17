class Solution {
    int i = 0;
    public boolean parseBoolExpr(String expression) {
        return solve(expression);
    }

    public boolean solve(String s){
        if(s.charAt(i) == 't'){
            i++;
            return true;
        }

        if(s.charAt(i) == 'f'){
            i++;
            return false;
        }

        char op = s.charAt(i);

        i+=2;

        boolean result;

        if(op == '&'){
            result =  true;
        } else {
            result =  false;
        }

        while(s.charAt(i) != ')'){
            if(s.charAt(i) == ','){
                i++;
                continue;
            }

            boolean value = solve(s);

            if(op == '&'){
                result = result && value;
            } else if(op == '|'){
                result = result || value;
            } else {
                result = !value;
            }
        }
        i++;
        return result;
    }
}