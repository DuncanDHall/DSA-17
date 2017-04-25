def regen(s):
    re = []
    p = " "
    count = 0
    for l in s:
        if p == l:
            if re[-1] != "*":
                re.append("*")
        else:
            re.append(l)
            p = l

    return "".join(re)

def badregen(s):
    re = []
    for l in s:
        re.append(l + "*")

    return "".join(re)

