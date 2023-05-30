package Default;

import java.util.Objects;

public class DNANode implements Comparable<DNANode> {
    private int name;

    public DNANode(String name) {
        this.name = Integer.parseInt(name);
    }

    public int getName() {
        return name;
    }

    @Override
    public int compareTo(DNANode o) {
        return Integer.compare(name, o.getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        DNANode other = (DNANode) obj;

        return name == other.getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return Integer.toString(name);
    }
	
}
