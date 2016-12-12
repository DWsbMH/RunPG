#!/bin/bash

rm szakdoga.aux szakdoga.pdf szakdoga.dvi szakdoga.out.ps szakdoga.ps szakdoga.bbl szakdoga.blg szakdoga.idx szakdoga.ilg szakdoga.ind szakdoga.loa szakdoga.lof szakdoga.log szakdoga.lot szakdoga.out szakdoga.toc szakdoga-blx.bib szakdoga.bcf

pdflatex szakdoga.tex
bibtex szakdoga
makeindex szakdoga.idx
dvips szakdoga.dvi
pdflatex szakdoga.tex
pdflatex szakdoga.tex
