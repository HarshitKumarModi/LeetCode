class Solution {
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        for(int i = 0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(map.containsKey(ch)){
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());

        list.sort((a,b) -> b.getValue() - a.getValue());

        StringBuilder result = new StringBuilder();

        for(Map.Entry<Character, Integer> entry : list){
            char c = entry.getKey();
            int frequency = entry.getValue();

            for(int i = 0; i<frequency; i++){
                result.append(c);
            }
        }
        return result.toString();
    }
}