package Model;

import Model.Value.RefValue;
import Model.Value.Value;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GarbageCollector {

    public Map<Integer,Value> safeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap){
        List<Integer> addreses = heap.values().stream()
                .filter(v->v instanceof RefValue)
                .map(v-> {
                    RefValue v1 = (RefValue) v;
                    return v1.getAddr();
                })
                .collect(Collectors.toList());

        symTableAddr.addAll(addreses);

        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer,Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value> heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static List<Integer> getAddrFromSymTable(List<PrgState> prgList)
    {
        List<Integer> symTableValues=null;
        for (PrgState current_prgstate : prgList) {
            List<Integer> addrs = current_prgstate.getSymTable().values().stream()
                    .filter(v -> v instanceof RefValue)
                    .map(v -> {
                        RefValue v1 = (RefValue) v;
                        return v1.getAddr();
                    }).collect(Collectors.toList());

            if (symTableValues == null) {
                symTableValues = Stream.concat(Stream.empty(), addrs.stream()).distinct().collect(Collectors.toList());

            } else
                symTableValues = Stream.concat(symTableValues.stream(), addrs.stream()).distinct().collect(Collectors.toList());
        }

        return symTableValues;


    }

}
