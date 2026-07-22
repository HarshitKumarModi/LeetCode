/**
 * @param {string} s
 * @return {boolean}
 */
var isValid = function(s) {
    let stack = [];
    for(let ch of s){
        if(ch == '(' || ch == '{' || ch == '['){
            stack.push(ch);
        } else {
            if(stack.length == 0){
                return false;
            }

            let top = stack.pop();

            if(ch == ')' && top!='('){
                return false;
            }
            if(ch == '}' && top!='{'){
                return false;
            }
            if(ch == ']' && top!='['){
                return false;
            }
        }
    }
    return stack.length == 0;
};